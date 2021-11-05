package es.marialamo.CDI;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped//con ApplicationScoped el valor de esta variable una vez se crea se uqeda con el valor en toda la vida de la aplicacion, con request crea un objeto en cada petición
public class ApplicationScopedDependency {

    private final Long timestamp;

    public ApplicationScopedDependency() {
        this.timestamp = System.currentTimeMillis();
    }

    Long getTimestamp() {
        return timestamp;
    }

}
