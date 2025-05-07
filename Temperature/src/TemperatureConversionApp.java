import ru.academits.eliseev.controllers.Controller;
import ru.academits.eliseev.models.Model;
import ru.academits.eliseev.views.View;

public class TemperatureConversionApp {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View("Конвертер температур");
        Controller controller = new Controller(model, view);
        controller.initController();
    }
}
