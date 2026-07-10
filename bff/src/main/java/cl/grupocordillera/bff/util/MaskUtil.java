package cl.grupocordillera.bff.util;

public class MaskUtil {

    public static String maskRut(String rut) {
        if (rut == null || rut.isBlank()) return "—";
        String clean = rut.replace(" ", "");
        String dv = clean.substring(clean.length() - 1);
        String body = clean.substring(0, clean.length() - 2).replace(".", "");
        if (body.length() <= 4) return "***-" + dv;
        String visibleStart = body.substring(0, 2);
        return visibleStart + ".***.***-" + dv;
    }

    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) return "—";
        String[] parts = email.split("@");
        String user = parts[0];
        String domain = parts[1];
        if (user.length() <= 2) return user.charAt(0) + "***@" + domain;
        String middle = "*".repeat(Math.max(1, user.length() - 2));
        return user.charAt(0) + middle + user.charAt(user.length() - 1) + "@" + domain;
    }

    public static String maskPhone(String phone) {
        if (phone == null) return "—";
        String digits = phone.replaceAll("\\D", "");
        if (digits.length() < 4) return "****";
        String start = digits.substring(0, Math.min(3, digits.length()));
        String end = digits.substring(digits.length() - 4);
        return "+" + start.substring(0, 2) + " " + start.substring(2) + " ****" + end;
    }
}
