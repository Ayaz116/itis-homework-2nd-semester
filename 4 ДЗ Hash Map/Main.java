import Iterator.ru.mustafin.collections.*;


public class Main {
    public static void main(String[] args) {
        ItMap<String,String> m = new ItMapImpl<>();

        m.put("russia", "moscow");
        m.put("usa", "washington");
        m.put("germany", "berlin");
        m.put("france", "paris");
        m.put("belarus", "bryansk");
        m.put("belarus", "minsk");
        m.put("belarus", "minsk");

        System.out.println(m.size());
        System.out.println(m.get("france"));
        System.out.println(m.get("russia"));
        System.out.println(m.get("australia"));
        System.out.println(m.get("belarus"));

        for (Node<String, String> node : m){
            System.out.println(node.getKey() + " " + node.getValue());
        }


}
}