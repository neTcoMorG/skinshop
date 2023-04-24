package mc.jun.skinshop;

import java.util.*;

public class Test {

    public String solution(String[] Dir, String[][] Cmd) {
        String answer = "";

        String command = "";
        String path = "";
        String currentPath = "C:/root/";

        for (int i=0; i<Cmd.length; i++) {
            command = Cmd[i][0];
            path = Cmd[i][1];


            //절대 경로
            if (path.contains("C:")) {
                List<String> list = Arrays.asList(Dir);
                if (list.contains(path)) {
                    return currentPath;
                }
                currentPath = path;
            }
            else {
                for (int j=0; j<Dir.length; j++) {
                    String[] split = Dir[j].split(currentPath);
                    if (split.length > 0) {
                        String dirName = split[1];
                        if (dirName.contains("/")) {
                            String[] tmp = dirName.split("/");
                            dirName = tmp[0];
                        }

                        if (dirName.equals(path)) {
                            
                        }
                    }

                }
            }
        }

        return answer;
    }
}
