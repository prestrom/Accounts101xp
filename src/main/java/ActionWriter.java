import java.io.*;
import org.apache.commons.io.FileUtils;

public class ActionWriter {

    public static void write(String str) throws IOException {
        File file = new File(App.path);
        FileUtils.writeStringToFile(file, str, "UTF-8");
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

   }

}
