//import com.lhogan.uvm2.Course;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//
//public class Scraping {
//
//
//    public static void main(String[] args) throws Exception {
//        URL url = new URL("https://giraffe.uvm.edu/~rgweb/batch/curr_enroll_spring.txt");
//        Scanner sc = new Scanner(url.openStream());
//        String[] titles = sc.nextLine().split(",");
//        ArrayList<Course> courses = new ArrayList<>();
//        BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"));
//        while (sc.hasNextLine()) {
//            String currentLine = sc.nextLine();
//            currentLine = currentLine.replace("\"", "");
//            String current[] = currentLine.split(",");
//            if (titles.length == 17) {
//                try {
//                    courses.add(new Course(current[0], current[1], current[2], current[3], current[4], current[5], current[6], current[7], current[8], current[9], current[10], current[11], current[12], current[13], current[14] + " " + current[15], current[16], current[17]));
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println("Staff Error");
//                }
//            } else {
//                try {
//                    courses.add(new Course(current[0], current[1], current[2], current[3], current[4], current[5], current[6], current[7], current[8], current[9], current[10], current[11], current[12], current[13], current[14], current[15], current[16] + " " + current[17], current[18], current[19]));
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println("Staff Error");
//                }
//
//            }
//        }
//        for (Course c : courses) {
//            writer.write(c.printer());
//        }
//    }
//}
