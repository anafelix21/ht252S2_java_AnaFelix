package vallegrade.edu.pe.utils;

import java.util.HashMap;
import java.util.Map;

public class ConfiguracionEquipos {
    private static final Map<String, Map<String, Map<String, int[]>>> configuraciones = new HashMap<>();

    static {
        configurarLaptops();
        configurarDesktops();
        configurarServidores();
        configurarImpresoras();
        configurarMonitores();
        configurarRouters();
    }

    private static void configurarLaptops() {
        Map<String, Map<String, int[]>> laptop = new HashMap<>();

        Map<String, int[]> hp = new HashMap<>();
        hp.put("Windows 10", new int[]{8, 512});
        hp.put("Windows 11", new int[]{16, 512});
        hp.put("macOS", new int[]{16, 512});
        hp.put("Linux", new int[]{8, 256});
        hp.put("Otro", new int[]{8, 256});
        laptop.put("HP", hp);

        Map<String, int[]> dell = new HashMap<>();
        dell.put("Windows 10", new int[]{8, 256});
        dell.put("Windows 11", new int[]{16, 512});
        dell.put("macOS", new int[]{16, 512});
        dell.put("Linux", new int[]{8, 256});
        dell.put("Otro", new int[]{8, 256});
        laptop.put("Dell", dell);

        Map<String, int[]> lenovo = new HashMap<>();
        lenovo.put("Windows 10", new int[]{8, 256});
        lenovo.put("Windows 11", new int[]{16, 512});
        lenovo.put("macOS", new int[]{0, 0});
        lenovo.put("Linux", new int[]{8, 256});
        lenovo.put("Otro", new int[]{8, 256});
        laptop.put("Lenovo", lenovo);

        Map<String, int[]> asus = new HashMap<>();
        asus.put("Windows 10", new int[]{8, 512});
        asus.put("Windows 11", new int[]{16, 512});
        asus.put("macOS", new int[]{0, 0});
        asus.put("Linux", new int[]{8, 256});
        asus.put("Otro", new int[]{8, 256});
        laptop.put("ASUS", asus);

        Map<String, int[]> apple = new HashMap<>();
        apple.put("Windows 10", new int[]{0, 0});
        apple.put("Windows 11", new int[]{0, 0});
        apple.put("macOS", new int[]{16, 512});
        apple.put("Linux", new int[]{0, 0});
        apple.put("Otro", new int[]{16, 512});
        laptop.put("Apple", apple);

        Map<String, int[]> otro = new HashMap<>();
        otro.put("Windows 10", new int[]{4, 128});
        otro.put("Windows 11", new int[]{8, 256});
        otro.put("macOS", new int[]{8, 256});
        otro.put("Linux", new int[]{4, 128});
        otro.put("Otro", new int[]{4, 128});
        laptop.put("Otro", otro);

        configuraciones.put("Laptop", laptop);
    }

    private static void configurarDesktops() {
        Map<String, Map<String, int[]>> desktop = new HashMap<>();

        Map<String, int[]> hp = new HashMap<>();
        hp.put("Windows 10", new int[]{16, 512});
        hp.put("Windows 11", new int[]{16, 1024});
        hp.put("macOS", new int[]{0, 0});
        hp.put("Linux", new int[]{8, 512});
        hp.put("Otro", new int[]{8, 256});
        desktop.put("HP", hp);

        Map<String, int[]> dell = new HashMap<>();
        dell.put("Windows 10", new int[]{16, 512});
        dell.put("Windows 11", new int[]{16, 1024});
        dell.put("macOS", new int[]{0, 0});
        dell.put("Linux", new int[]{8, 512});
        dell.put("Otro", new int[]{8, 256});
        desktop.put("Dell", dell);

        Map<String, int[]> asus = new HashMap<>();
        asus.put("Windows 10", new int[]{16, 512});
        asus.put("Windows 11", new int[]{32, 1024});
        asus.put("macOS", new int[]{0, 0});
        asus.put("Linux", new int[]{16, 512});
        asus.put("Otro", new int[]{8, 256});
        desktop.put("ASUS", asus);

        Map<String, int[]> otro = new HashMap<>();
        otro.put("Windows 10", new int[]{8, 256});
        otro.put("Windows 11", new int[]{16, 512});
        otro.put("macOS", new int[]{0, 0});
        otro.put("Linux", new int[]{8, 256});
        otro.put("Otro", new int[]{4, 128});
        desktop.put("Otro", otro);

        configuraciones.put("Desktop", desktop);
    }

    private static void configurarServidores() {
        Map<String, Map<String, int[]>> servidor = new HashMap<>();

        Map<String, int[]> dell = new HashMap<>();
        dell.put("Windows 10", new int[]{32, 2048});
        dell.put("Windows 11", new int[]{32, 2048});
        dell.put("Ubuntu Server", new int[]{32, 2048});
        dell.put("Linux", new int[]{32, 2048});
        dell.put("Otro", new int[]{16, 1024});
        servidor.put("Dell", dell);

        Map<String, int[]> hp = new HashMap<>();
        hp.put("Windows 10", new int[]{32, 2048});
        hp.put("Windows 11", new int[]{32, 2048});
        hp.put("Ubuntu Server", new int[]{32, 2048});
        hp.put("Linux", new int[]{32, 2048});
        hp.put("Otro", new int[]{16, 1024});
        servidor.put("HP", hp);

        Map<String, int[]> otro = new HashMap<>();
        otro.put("Windows 10", new int[]{16, 1024});
        otro.put("Windows 11", new int[]{16, 1024});
        otro.put("Ubuntu Server", new int[]{16, 1024});
        otro.put("Linux", new int[]{16, 1024});
        otro.put("Otro", new int[]{8, 512});
        servidor.put("Otro", otro);

        configuraciones.put("Servidor", servidor);
    }

    private static void configurarImpresoras() {
        Map<String, Map<String, int[]>> impresora = new HashMap<>();

        Map<String, int[]> hp = new HashMap<>();
        hp.put("Windows 10", new int[]{2, 64});
        hp.put("Windows 11", new int[]{2, 64});
        hp.put("macOS", new int[]{2, 64});
        hp.put("Linux", new int[]{2, 64});
        hp.put("Otro", new int[]{2, 64});
        impresora.put("HP", hp);

        Map<String, int[]> canon = new HashMap<>();
        canon.put("Windows 10", new int[]{2, 64});
        canon.put("Windows 11", new int[]{2, 64});
        canon.put("macOS", new int[]{2, 64});
        canon.put("Linux", new int[]{2, 64});
        canon.put("Otro", new int[]{2, 64});
        impresora.put("Canon", canon);

        Map<String, int[]> otro = new HashMap<>();
        otro.put("Windows 10", new int[]{2, 64});
        otro.put("Windows 11", new int[]{2, 64});
        otro.put("macOS", new int[]{2, 64});
        otro.put("Linux", new int[]{2, 64});
        otro.put("Otro", new int[]{2, 64});
        impresora.put("Otro", otro);

        configuraciones.put("Impresora", impresora);
    }

    private static void configurarMonitores() {
        Map<String, Map<String, int[]>> monitor = new HashMap<>();

        Map<String, int[]> samsung = new HashMap<>();
        samsung.put("Windows 10", new int[]{0, 0});
        samsung.put("Windows 11", new int[]{0, 0});
        samsung.put("macOS", new int[]{0, 0});
        samsung.put("Linux", new int[]{0, 0});
        samsung.put("Otro", new int[]{0, 0});
        monitor.put("Samsung", samsung);

        Map<String, int[]> lg = new HashMap<>();
        lg.put("Windows 10", new int[]{0, 0});
        lg.put("Windows 11", new int[]{0, 0});
        lg.put("macOS", new int[]{0, 0});
        lg.put("Linux", new int[]{0, 0});
        lg.put("Otro", new int[]{0, 0});
        monitor.put("LG", lg);

        Map<String, int[]> otro = new HashMap<>();
        otro.put("Windows 10", new int[]{0, 0});
        otro.put("Windows 11", new int[]{0, 0});
        otro.put("macOS", new int[]{0, 0});
        otro.put("Linux", new int[]{0, 0});
        otro.put("Otro", new int[]{0, 0});
        monitor.put("Otro", otro);

        configuraciones.put("Monitor", monitor);
    }

    private static void configurarRouters() {
        Map<String, Map<String, int[]>> router = new HashMap<>();

        Map<String, int[]> tplink = new HashMap<>();
        tplink.put("Windows 10", new int[]{0, 16});
        tplink.put("Windows 11", new int[]{0, 16});
        tplink.put("macOS", new int[]{0, 16});
        tplink.put("Linux", new int[]{0, 16});
        tplink.put("Otro", new int[]{0, 16});
        router.put("TP-Link", tplink);

        Map<String, int[]> otro = new HashMap<>();
        otro.put("Windows 10", new int[]{0, 16});
        otro.put("Windows 11", new int[]{0, 16});
        otro.put("macOS", new int[]{0, 16});
        otro.put("Linux", new int[]{0, 16});
        otro.put("Otro", new int[]{0, 16});
        router.put("Otro", otro);

        configuraciones.put("Router", router);
    }

    public static Integer obtenerRAM(String tipo, String marca, String so) {
        if (tipo == null || marca == null || so == null) return null;
        
        Map<String, Map<String, int[]>> tipoConfig = configuraciones.get(tipo);
        if (tipoConfig == null) tipoConfig = configuraciones.get("Laptop");
        
        Map<String, int[]> marcaConfig = tipoConfig.get(marca);
        if (marcaConfig == null) marcaConfig = tipoConfig.get("Otro");
        
        int[] valores = marcaConfig.get(so);
        if (valores == null) valores = marcaConfig.get("Otro");
        
        return valores != null ? valores[0] : null;
    }

    public static Integer obtenerAlmacenamiento(String tipo, String marca, String so) {
        if (tipo == null || marca == null || so == null) return null;
        
        Map<String, Map<String, int[]>> tipoConfig = configuraciones.get(tipo);
        if (tipoConfig == null) tipoConfig = configuraciones.get("Laptop");
        
        Map<String, int[]> marcaConfig = tipoConfig.get(marca);
        if (marcaConfig == null) marcaConfig = tipoConfig.get("Otro");
        
        int[] valores = marcaConfig.get(so);
        if (valores == null) valores = marcaConfig.get("Otro");
        
        return valores != null ? valores[1] : null;
    }
}
