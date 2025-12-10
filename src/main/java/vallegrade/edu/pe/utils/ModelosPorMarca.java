package vallegrade.edu.pe.utils;

import java.util.*;

public class ModelosPorMarca {
    private static final Map<String, List<String>> modelos = new HashMap<>();

    static {
        modelos.put("HP", Arrays.asList(
                "HP Pavilion 15",
                "HP ProBook 450",
                "HP Envy 13",
                "HP ZBook Fury",
                "HP OptiPlex 7090",
                "HP Elite 8300",
                "HP Color LaserJet Pro",
                "HP DeskJet 3755"
        ));

        modelos.put("Dell", Arrays.asList(
                "Dell Inspiron 15",
                "Dell XPS 13",
                "Dell Latitude 5000",
                "Dell Precision 5000",
                "Dell OptiPlex 7090",
                "Dell Vostro 3590",
                "Dell PowerEdge R750",
                "Dell Printer S2720DGF"
        ));

        modelos.put("Lenovo", Arrays.asList(
                "Lenovo ThinkPad E14",
                "Lenovo IdeaPad 5",
                "Lenovo Yoga 9i",
                "Lenovo ThinkBook 14",
                "Lenovo ThinkCentre M90",
                "Lenovo IdeaCentre 5",
                "Lenovo ThinkSystem SR750",
                "Lenovo Printer M7215"
        ));

        modelos.put("ASUS", Arrays.asList(
                "ASUS VivoBook 15",
                "ASUS ROG Zephyrus G14",
                "ASUS TUF Gaming A15",
                "ASUS ProArt StudioBook",
                "ASUS ExpertCenter D7",
                "ASUS PA248Q",
                "ASUS PA279CV",
                "ASUS Print M6100 Series"
        ));

        modelos.put("Apple", Arrays.asList(
                "MacBook Air M1",
                "MacBook Air M2",
                "MacBook Pro 13\"",
                "MacBook Pro 14\"",
                "MacBook Pro 16\"",
                "Mac Mini",
                "iMac 24\"",
                "iMac 27\""
        ));

        modelos.put("TP-Link", Arrays.asList(
                "TP-Link Archer C6",
                "TP-Link Archer AX12",
                "TP-Link Deco M5",
                "TP-Link TL-WR940N"
        ));

        modelos.put("Otro", Collections.singletonList("Modelo Generico"));
    }

    public static List<String> obtenerModelos(String marca) {
        if (marca == null || marca.isEmpty()) {
            return modelos.get("Otro");
        }
        return modelos.getOrDefault(marca, modelos.get("Otro"));
    }

    public static boolean existeMarca(String marca) {
        return modelos.containsKey(marca);
    }
}
