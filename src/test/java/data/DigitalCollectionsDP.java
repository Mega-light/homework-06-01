package data;

import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;

public class DigitalCollectionsDP {
    @DataProvider(name = "searchFor-provider")
    public static Object @NotNull [] @NotNull [] searchForData(){
        int searchingDesired = 3;
        Object[][] objects = new Object[searchingDesired][1];
        objects[0][0] = "Abraham Lincoln";;
        objects[1][0] = "Benjamin Franklin Papers";
        objects[2][0] = "Earth Day";
        return objects;
    }
}
