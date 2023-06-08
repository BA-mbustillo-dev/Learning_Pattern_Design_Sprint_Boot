package banco.me.solicitud.endpoint;

import banco.me.generalValidation.domain.GeneralRequest;
import banco.me.generalValidation.domain.GeneralResponse;
import banco.me.generalValidation.domain.StateResponse;
import banco.me.generalValidation.service.ValidationService;
import banco.me.solicitud.domain.ArchivoEncabezado;
import banco.me.solicitud.domain.BitacoraErrores;
import banco.me.solicitud.domain.CatTipoArchivo;
import banco.me.solicitud.domain.CatTipoSolicitud;
import banco.me.solicitud.domain.GenEmpresaSeguroSocial;
import banco.me.solicitud.domain.TraArchivo;
import banco.me.solicitud.domain.Tra_Solicitud;
import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import banco.me.solicitud.domain.fnFormatoArchivoRelacionCliente;
import banco.me.solicitud.dto.BitacoraDto;
import banco.me.solicitud.dto.StructureGeneralClientData;
import banco.me.solicitud.pattern.chainofresponsability.EmpresaValidationResponsabilityInt;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking.CatTipoArchivoValidator;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking.CatTipoSolicitudValidator;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking.EmpresaValidator;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking.MedioCargaValidator;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking.RelacionEmpresaMedioCargaValidator;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking.RelacionEmpresaServicioValidator;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking.ServicioValidator;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ValidationInputEspecify.CodigoEmpresa;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ValidationInputEspecify.CodigoMedioCarga;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ValidationInputEspecify.CodigoPantalla;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ValidationInputEspecify.CodigoServicio;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ValidationInputEspecify.CodigoTipoArchivo;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ValidationInputEspecify.NombreArchivo;
import banco.me.solicitud.pattern.chainofresponsability.ValidationState.ValidationInputEspecify.NumeroCuentaOrigenDebito;
import banco.me.solicitud.pattern.chainofresponsability.handlerResponsabilityInter;
import banco.me.solicitud.service.LecturaArchivoService;
import banco.me.solicitud.service.UtilitySolicitudInter;
import banco.me.solicitud.service.solicitudValidacionService;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.springframework.beans.factory.annotation.Value;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.EstadoType;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.GeneralRequestType;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.ObjectFactory;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.SolicitudArchivoRequestType;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.SolicitudArchivoResponseType;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.SolicitudArchivoRequest;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.SolicitudArchivoResponse;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Endpoint
public class SolicitudEndPoint {

    @Value("${me.rest.bitacora.uri}")
    private String uriBitacora;

    @Value("${directory.file.url.input}")
    private String uriFileInput;

    @Value("${me.rest.validacionplanilla.uri}")
    private String uriVerificacionPlanilla;

    @Value("${me.rest.validacionbeneficiario.uri}")
    private String uriVerificacionBeneficiario;

    @Value("${parametro.empresa.seguro}")
    private String paramEmpresaSS;

    @Value("${spring.application.name}")
    private String appName;

    private static final String NAMESPACE_URI = "http://banco.me/ws/v1/services/WS_TRA_Solicitud_Archivo";

    @Autowired
    solicitudValidacionService solicitudValidacionService;

    @Autowired
    ValidationService ValidationService;

    @Autowired
    LecturaArchivoService lecturaArchivoService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UtilitySolicitudInter utilitySolicitudInter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SolicitudArchivoRequest")
    @ResponsePayload
    public JAXBElement<SolicitudArchivoResponse> consultaSolicitud(@RequestPayload JAXBElement<SolicitudArchivoRequest> request) {

        String alfanumericRegex = "^[0-9A-Za-z_&#´$ \\/?:().,+\\-\\s]{1,500}$";

        StructureGeneralClientData empresaValidacionSolicitud = new StructureGeneralClientData();
        CatTipoSolicitud catTipoSolicitud = new CatTipoSolicitud();
        CatTipoArchivo catTipoArchivo = new CatTipoArchivo();
        Tra_Solicitud traSolicitudNuevo = new Tra_Solicitud();
        TraArchivo traArchivo = new TraArchivo();
        GeneralRequestType generalElement = new GeneralRequestType();
        SolicitudArchivoRequestType especifyElement;
        StateResponse estadoResponse;
        Boolean validacion = true;
        String codigoEstado = "0000", idioma, accion = "", extension = "", nameFileFinal = "";
        Integer extnum;

        JAXBElement<SolicitudArchivoResponse> responseService = null;

        try {
            generalElement = request.getValue().getGeneralRequestElement();
            especifyElement = request.getValue().getSolicitudArchivoRequestElement();

            ObjectFactory factory = new ObjectFactory();
            SolicitudArchivoResponse response = factory.createSolicitudArchivoResponse();

            GeneralResponse responseDataValidacionGeneral = ValidationService.GeneralValidation(utilitySolicitudInter.createPartDataGeneralRequest(generalElement));

            BitacoraDto firstRowInputBitacoraRequest = utilitySolicitudInter.createDataBitacora(responseDataValidacionGeneral);
            restTemplate.postForEntity(uriBitacora + "json", firstRowInputBitacoraRequest, String.class);

            handlerResponsabilityInter validationCodigoServicio = new CodigoServicio();
            handlerResponsabilityInter validationNumeroCuentaOrigen = new NumeroCuentaOrigenDebito(validationCodigoServicio);
            handlerResponsabilityInter validationCodigoMedioCarga = new CodigoMedioCarga(validationNumeroCuentaOrigen);
            handlerResponsabilityInter validationCodigoPantalla = new CodigoPantalla(validationCodigoMedioCarga);
            handlerResponsabilityInter validationNombreArchivo = new NombreArchivo(validationCodigoPantalla);
            handlerResponsabilityInter validationCodigoTipoArchivo = new CodigoTipoArchivo(validationNombreArchivo);
            handlerResponsabilityInter validationCodigoEmpresa = new CodigoEmpresa(validationCodigoTipoArchivo);

            codigoEstado = validationCodigoEmpresa.validationDataStateInputEspecifyRequest(especifyElement);

            if (codigoEstado.equals("0000")) {
                String codigoBanco = "01";
                empresaValidacionSolicitud.setFnEmpresaValidacion(solicitudValidacionService.findEmpresaValidacion(responseDataValidacionGeneral.idCanal, especifyElement.getCodigoMedioCarga(), especifyElement.getCodigoServicio(), especifyElement.getCodigoEmpresa(), responseDataValidacionGeneral.idMotor, codigoBanco, responseDataValidacionGeneral.idInstitucion, responseDataValidacionGeneral.idCliente));
                empresaValidacionSolicitud.setCatTipoSolicitud(solicitudValidacionService.finByTipoSolicitud(responseDataValidacionGeneral.idMotor, responseDataValidacionGeneral.idInstitucion, accion));
                empresaValidacionSolicitud.setCatTipoArchivo(solicitudValidacionService.findByTipoArchivo(responseDataValidacionGeneral.idMotor, especifyElement.getCodigoTipoArchivo()));

                EmpresaValidationResponsabilityInt catTipoArchivoValidacion = new CatTipoArchivoValidator();
                EmpresaValidationResponsabilityInt catTipoSolicitudValidator = new CatTipoSolicitudValidator(catTipoArchivoValidacion);
                EmpresaValidationResponsabilityInt relacionEmpresaServicioValidacion = new RelacionEmpresaServicioValidator(catTipoSolicitudValidator);
                EmpresaValidationResponsabilityInt servicioValidacion = new ServicioValidator(relacionEmpresaServicioValidacion);
                EmpresaValidationResponsabilityInt relacionMedioCargaEmpresaValidacion = new RelacionEmpresaMedioCargaValidator(servicioValidacion);
                EmpresaValidationResponsabilityInt medioCargaValidacion = new MedioCargaValidator(relacionMedioCargaEmpresaValidacion);
                EmpresaValidationResponsabilityInt relacionEmpresaCanalValidacion = new RelacionEmpresaMedioCargaValidator(medioCargaValidacion);
                EmpresaValidationResponsabilityInt EmpresaValidacion = new EmpresaValidator(relacionEmpresaCanalValidacion);

                codigoEstado = EmpresaValidacion.ClientDataValidation(empresaValidacionSolicitud);
            }

            if (codigoEstado.equals("0000")) {
                Integer idEstadoProceso = 1;
                long contadorLinea = 0;
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyMMddHHmmss");
                extnum = especifyElement.getNombreArchivo().lastIndexOf(".");
                extension = especifyElement.getNombreArchivo().substring(extnum + 1);
                fnFormatoArchivoRelacionCliente formatoArchivoCliente = new fnFormatoArchivoRelacionCliente();
                List<Object> listaDetalle = new ArrayList<>();

                String nombreArchivoB2b = especifyElement.getNombreArchivo();

                //patron para buscar el tercer guion bajo (_)
                String patronNombreOrigen = "^(?:[^_]*_){3}";

                //se reemplaza del nombre completo los primeros caracteres hasta el tercer guion bajo para obtener el valor del nombreArchivoOrigen
                String nombreArchivoOrigen = nombreArchivoB2b.replaceAll(patronNombreOrigen, "");

                //patrin para  buscar el segundo guion bajo (_)
                Pattern patronNombreFinal = Pattern.compile("^(?:[^_]*_){2}");

                //se aplica la expresion regular a la variable  nombreArchivoB2b
                Matcher matcher = patronNombreFinal.matcher(nombreArchivoB2b);
                matcher.find();

                //se concatenan los caracteres antes del correlativo junto con el nombre del archivo origen
                String nombreArchivoFinal = matcher.group() + nombreArchivoOrigen;

                traArchivo = lecturaArchivoService.getNombreArchivo(nombreArchivoOrigen);
                if (traArchivo == null) {

                    if (extension.equals("txt")) {
                        contadorLinea = contadorLinea(uriFileInput + especifyElement.getNombreArchivo().replaceAll(" ", "%20"));
                        if (contadorLinea > 0) {
                            GenEmpresaSeguroSocial empresaSS = new GenEmpresaSeguroSocial();
                            empresaSS = lecturaArchivoService.getEmpresaSS(paramEmpresaSS, responseDataValidacionGeneral.idInstitucion);
                            if (empresaSS.getCodigoEmpresa().equals(empresaValidacionSolicitud.getFnEmpresaValidacion().getCodigoEmpresa().toUpperCase())) {
                                formatoArchivoCliente = lecturaArchivoService.findFormatoArchivoTxtSeguroSocial(responseDataValidacionGeneral.idCliente, empresaValidacionSolicitud.getFnEmpresaValidacion().getIdEmpresa(),
                                        catTipoArchivo.getIdTipoArchivo().intValue(), contadorLinea, responseDataValidacionGeneral.idInstitucion, responseDataValidacionGeneral.idMotor, extension);
                            } else {
                                formatoArchivoCliente = lecturaArchivoService.findFormatoArchivoTxtCliente(responseDataValidacionGeneral.idCliente, empresaValidacionSolicitud.getFnEmpresaValidacion().getIdEmpresa(),
                                        catTipoArchivo.getIdTipoArchivo().intValue(), contadorLinea, responseDataValidacionGeneral.idInstitucion, responseDataValidacionGeneral.idMotor, extension);
                            }
                        } else {
                            codigoEstado = "5241";
                        }
                    } else if (extension.equals("xlsx")) {
                        contadorLinea = contadorColumnas(uriFileInput + especifyElement.getNombreArchivo().replaceAll(" ", "%20"));
                        if (contadorLinea > 0) {
                            formatoArchivoCliente = lecturaArchivoService.findFormatoArchivoXlsxCliente(responseDataValidacionGeneral.idCliente, empresaValidacionSolicitud.getFnEmpresaValidacion().getIdEmpresa(),
                                    catTipoArchivo.getIdTipoArchivo().intValue(), contadorLinea, responseDataValidacionGeneral.idInstitucion, responseDataValidacionGeneral.idMotor, extension);
                        } else {
                            codigoEstado = "5241";
                        }
                    } else {
                        codigoEstado = "5435";
                    }

                    if (codigoEstado.equals("0000")) {
                        if (formatoArchivoCliente != null) {
                            if (formatoArchivoCliente.getIdFormatoArchivo() == null || formatoArchivoCliente.equals("")) {
                                codigoEstado = "2004";
                            } else if (formatoArchivoCliente.getIdEstadoFormatoArchivo() == null || formatoArchivoCliente.getIdEstadoFormatoArchivo().equals("")) {
                                codigoEstado = "5391";
                            } else if (formatoArchivoCliente.getIdRelacionClienteArchivoFormato() == null || formatoArchivoCliente.getIdRelacionClienteArchivoFormato().equals("")) {
                                codigoEstado = "6007";
                            } else if (formatoArchivoCliente.getIdEstadoRelacionFormatoCliente() == null || formatoArchivoCliente.getIdEstadoRelacionFormatoCliente().equals("")) {
                                codigoEstado = "5432";
                            }
                        } else {
                            codigoEstado = "5099";
                        }
                    }

                } else {
                    codigoEstado = "1076";
                }

                if (codigoEstado.equals("0000")) {
                    try {
                        listaDetalle = lecturaArchivoService.lecturaArchivo(uriFileInput + especifyElement.getNombreArchivo().replaceAll(" ", "%20"),
                                extension, contadorLinea, accion, formatoArchivoCliente.getCodigoFormatoArchivo());
                        System.out.println("**************************************************************************");
                        Gson gson = new Gson();
                        System.out.println(gson.toJson(listaDetalle));
                        System.out.println("**************************************************************************");

                    } catch (Exception e) {
                        System.out.println("Error movimiento Archivo: " + e.getMessage());
                        System.out.println(e.toString());
                    }
                }

                Tra_Solicitud solicitud = new Tra_Solicitud();
                if (!codigoEstado.equals("0000")) {
                    solicitud.setFechaRechazo(LocalDateTime.now());
                    solicitud.setUsuarioRechazo("System");
                    idEstadoProceso = 5;
                }
                solicitud.setIdInstitucion(responseDataValidacionGeneral.idInstitucion);
                solicitud.setIdMotor(responseDataValidacionGeneral.idMotor);
                solicitud.setIdCliente(responseDataValidacionGeneral.idCliente);
                solicitud.setIdArchivo(Integer.parseInt(responseDataValidacionGeneral.transaccionId));
                solicitud.setAccion(accion);
                solicitud.setIdEstadoProceso(idEstadoProceso);
                solicitud.setUsuarioSolicita("mbustillo");
                solicitud.setIdEmpresa(empresaValidacionSolicitud.getFnEmpresaValidacion().getIdEmpresa());
                solicitud.setNombreArchivo(especifyElement.getNombreArchivo());
                solicitud.setComentario(especifyElement.getComentario());
                solicitud.setNumeroCuenta(especifyElement.getCuentaOrigenDebito());
                solicitud.setMonto(especifyElement.getMonto().doubleValue());
                solicitud.setIdCanal(responseDataValidacionGeneral.idCanal);
                solicitud.setIdTipoArchivo(catTipoArchivo.getIdTipoArchivo().intValue());
                solicitud.setCodigoMoneda(empresaValidacionSolicitud.getFnEmpresaValidacion().getCodigoMoneda());
                solicitud.setEstadoArchivo(codigoEstado);
                solicitud.setIdTipoSolicitud(catTipoSolicitud.getIdTipoSolicitud().intValue());
                solicitud.setIdServicio(empresaValidacionSolicitud.getFnEmpresaValidacion().getIdServicio().intValue());
                solicitud.setPantalla(especifyElement.getPantalla());
                solicitud.setCodigoServicio(especifyElement.getCodigoServicio().toUpperCase());

                traSolicitudNuevo = solicitudValidacionService.saveSolicitudArchivo(solicitud);

                if (traSolicitudNuevo != null && codigoEstado == "0000") {
                    ArchivoEncabezado dataValidacion = new ArchivoEncabezado();

                    dataValidacion.setCodigoBusinessPartner(responseDataValidacionGeneral.codigoBusinessPartner);
                    dataValidacion.setCodigoTipoArchivo(catTipoArchivo.getCodigoTipoArchivo());
                    dataValidacion.setExtensionArchivo(extension);
                    dataValidacion.setNombreArchivoB2B(nombreArchivoB2b);
                    dataValidacion.setNombreArchivoOriginal(nombreArchivoOrigen);
                    dataValidacion.setNombreArchivoFinal(nombreArchivoFinal);
                    dataValidacion.setEventId(traSolicitudNuevo.getIdArchivo().toString());
                    dataValidacion.setIdSolicitud(traSolicitudNuevo.getIdSolicitud());
                    dataValidacion.setIdCliente(responseDataValidacionGeneral.idCliente);
                    dataValidacion.setIdInstitucion(traSolicitudNuevo.getIdInstitucion());
                    dataValidacion.setIdMotor(traSolicitudNuevo.getIdMotor());
                    dataValidacion.setIdEmpresa(traSolicitudNuevo.getIdEmpresa());
                    dataValidacion.setIdCanal(traSolicitudNuevo.getIdCanal());
                    dataValidacion.setNumeroCuenta(traSolicitudNuevo.getNumeroCuenta());
                    dataValidacion.setMontoSolicitud(traSolicitudNuevo.getMonto());
                    dataValidacion.setIdServicio(traSolicitudNuevo.getIdServicio());
                    dataValidacion.setCodigoMoneda(traSolicitudNuevo.getCodigoMoneda());
                    dataValidacion.setCodigoServicio(traSolicitudNuevo.getCodigoServicio());
                    dataValidacion.setCodigoCanal(responseDataValidacionGeneral.codigoCanal);
                    dataValidacion.setIdMoneda(empresaValidacionSolicitud.getFnEmpresaValidacion().getIdMoneda());
                    dataValidacion.setCodigoBancoSAP(empresaValidacionSolicitud.getFnEmpresaValidacion().getCodigoBancoSAP());
                    dataValidacion.setCodigoPais(empresaValidacionSolicitud.getFnEmpresaValidacion().getCodigoPais2());
                    dataValidacion.setIdTipoArchivo(traSolicitudNuevo.getIdTipoArchivo());
                    dataValidacion.setIdFormatoArchivo(formatoArchivoCliente.getIdFormatoArchivo());
                    dataValidacion.setCodigoFormatoArchivo(formatoArchivoCliente.getCodigoFormatoArchivo());
                    dataValidacion.setCodigoEmpresa(empresaValidacionSolicitud.getFnEmpresaValidacion().getCodigoEmpresa());
                    dataValidacion.setNombreEmpresa(empresaValidacionSolicitud.getFnEmpresaValidacion().getNombreEmpresa());
                    dataValidacion.setCodigoIdioma("ES");
                    dataValidacion.setIdPais(responseDataValidacionGeneral.idPais);
                    dataValidacion.setCodigoBanco(empresaValidacionSolicitud.getFnEmpresaValidacion().getCodigoBanco());
                    dataValidacion.setIdBancoPais(empresaValidacionSolicitud.getFnEmpresaValidacion().getIdBancoPais());
                    dataValidacion.setDescripcionTipoArchivo("ES".equals("ES") ? formatoArchivoCliente.getDescripcionFormatoArchivo() : formatoArchivoCliente.getDescripcionFormatoArchivoEn());
                    dataValidacion.setIndTipoProcesamiento(1);   // este valor se esta mandando quemado
                    dataValidacion.setDescripcionFormatoExtension("ES".equals("ES") ? catTipoArchivo.getDescripcionTipoArchivo() : catTipoArchivo.getDescripcionTipoArchivoEn() + extension);
                    dataValidacion.setUsuarioInsercion("mbustillo");
                    dataValidacion.setDetalle(listaDetalle);

                    if (accion.equals("B")) {
                        ResponseEntity<String> responseArchivo = restTemplate.postForEntity(uriVerificacionBeneficiario, dataValidacion, String.class);
                        codigoEstado = responseArchivo.getBody();
                    } else {
                        ResponseEntity<String> responseArchivo = restTemplate.postForEntity(uriVerificacionPlanilla, dataValidacion, String.class);
                        codigoEstado = responseArchivo.getBody();
                    }

                    Gson gson = new Gson();
                    String json = gson.toJson(dataValidacion);
                    System.out.println(json);

                    SolicitudArchivoResponseType solicitudArchivo = new SolicitudArchivoResponseType();
                    solicitudArchivo.setCodigoEmpresa(empresaValidacionSolicitud.getFnEmpresaValidacion().getCodigoEmpresa());
                    solicitudArchivo.setComentario(especifyElement.getComentario());
                    solicitudArchivo.setDescripcionTransaccion(especifyElement.getComentario());
                    solicitudArchivo.setEstadoArchivo(codigoEstado);
                    solicitudArchivo.setFecha(LocalDate.now().toString());
                    solicitudArchivo.setHora(String.valueOf(LocalDateTime.now().format(timeFormat)));
                    solicitudArchivo.setMonto(BigDecimal.valueOf(traSolicitudNuevo.getMonto()));
                    solicitudArchivo.setNombreEmpresa(empresaValidacionSolicitud.getFnEmpresaValidacion().getNombreEmpresa());
                    solicitudArchivo.setNumeroCuenta(especifyElement.getCuentaOrigenDebito());
                    solicitudArchivo.setNumeroTransaccion(BigInteger.valueOf(traSolicitudNuevo.getIdSolicitud()));
                    response.setSolicitudArchivoElement(solicitudArchivo);
                }
            }

            estadoResponse = ValidationService.getState(codigoEstado, "ES");
            EstadoType estadoElement = factory.createEstadoType();
            estadoElement.setCodigo(estadoResponse.getCodigoEstado());
            estadoElement.setDescripcion(estadoResponse.getDescripcionEstado());
            estadoElement.setDetalleTecnico(estadoResponse.getDescripcionEstadoTecnico());
            estadoElement.setTipo(estadoResponse.getTipoEstado());

            response.setEstado(estadoElement);

            responseService = new JAXBElement<SolicitudArchivoResponse>(new QName(NAMESPACE_URI, "SolicitudArchivoResponse"), SolicitudArchivoResponse.class, response);

            firstRowInputBitacoraRequest.setTipoDetalle("Response/XML");
            firstRowInputBitacoraRequest.setTransaccionXML(responseService.getValue());
            restTemplate.postForEntity(uriBitacora + "json", firstRowInputBitacoraRequest, String.class);
        } catch (Exception e) {
            String stacktrace = ExceptionUtils.getStackTrace(e);
            BitacoraErrores bitacoraErrores = new BitacoraErrores();
            bitacoraErrores.setIdInstitucion(generalElement.getInstitucionId());
            bitacoraErrores.setIdCanal(generalElement.getCanalId());
            bitacoraErrores.setIdMotor(generalElement.getMotorId());
            bitacoraErrores.setIdUsuario(generalElement.getUsuarioId());
            bitacoraErrores.setTablaPrograma(appName);
            bitacoraErrores.setUbicacion(stacktrace);
            bitacoraErrores.setObservacion(e.toString());
            bitacoraErrores.setIdEstadoRegistro("ACT");

            restTemplate.postForEntity(uriBitacora + "errores", bitacoraErrores, String.class);
            throw new RuntimeException("Ha ocurrido en error " + e.toString());
        }
        return responseService;
    }

    public long contadorLinea(String url) {
        BufferedReader lector;
        String cadena;
        long tam = 0;

        try {
            URL ruta = new URL(url);
            lector = new BufferedReader(new InputStreamReader(ruta.openStream()));
            if ((cadena = lector.readLine()) != null) {
                tam = cadena.length();
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return tam;
    }

    public long contadorColumnas(String url) {
        long numColumnas = 0;

        try {
            URL ruta = new URL(url);
            InputStream file = ruta.openStream();
            XSSFWorkbook libro = new XSSFWorkbook(file);
            XSSFSheet sheet = libro.getSheetAt(0);
            Row fila = sheet.getRow(5);
            numColumnas = fila.getLastCellNum();
            libro.close();
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return numColumnas;
    }

}
