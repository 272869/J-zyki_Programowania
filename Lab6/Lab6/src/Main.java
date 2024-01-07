public class Main {
    public static void main(String[] args) {
        if(args.length == 0){
            throw new RuntimeException("Podaj argument");
        }
        if(args[0].matches("[1-9]") || args[0].matches("[1-9][0-9]")){
            int amount = Integer.parseInt(args[0]);
            if(amount < 1){
                throw new RuntimeException("Wartość musi być większa od 0");
            }
            if(amount > 10){
                throw new RuntimeException("Wartość musi być mniejsza bądź równa 10");
            }
            SimulationPanel.amnt = amount;
            new MainFrame();
        }else {
            throw new RuntimeException("Argument musi być liczbą z przedziału 1-10");
        }

    }
}