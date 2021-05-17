public class EmailApp {
    public static void main(String[] args) {
        Email em1 = new Email("Will","Smith");

        em1.setAlternateEmail("js@gmail.com");
        System.out.println(em1.getAlternateEmail());
        System.out.println(em1.showInfo());

    }
}
