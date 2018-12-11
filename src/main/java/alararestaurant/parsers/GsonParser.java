package alararestaurant.parsers;

public interface GsonParser  {

    String toString(Object object);

    <T> T fromString(String str, Class<T> klass);
}
