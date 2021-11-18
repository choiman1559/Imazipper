import com.imazipper.lib.Combiner;
import com.imazipper.lib.Splitter;
import com.imazipper.lib.TaskResult;
import picocli.CommandLine;

import java.io.File;
import java.util.Locale;

public class MainClass {
    @CommandLine.Command(name = "Imazipper CLI", helpCommand = true, description = "De/Combines Image and archive.")
    static class Parameters {
        @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this help message")
        public boolean helpRequested = false;

        @CommandLine.Option(names = {"-v", "--verbose"}, description = "print more detail logs.")
        public boolean isVerbose = false;

        @CommandLine.Option(names = {"-t", "--type"}, description = "selects type of task, only allows [\"combine\"/\"split\"] option.")
        public String JobType = "";

        @picocli.CommandLine.Option(names = {"-i", "--image"}, paramLabel = "FILE", description = "target image file to combine (Combine task only option)")
        public File imageInputFile = null;

        @picocli.CommandLine.Option(names = {"-a", "--archive"}, paramLabel = "FILE", description = "target archive file to combine (Combine task only option)")
        public File archiveInputFile = null;

        @picocli.CommandLine.Option(names = {"-f", "--file"}, paramLabel = "FILE", description = "target file to split (Split task only option)")
        public File splitInputFile = null;

        @picocli.CommandLine.Option(names = {"-o", "--output"}, paramLabel = "FILE/FOLDER", description = "target File/Folder to save your task output\nIf file/folder doesn't specified, output file/folder will automatically specified.")
        public File taskOutputFile = null;
    }

    public static void main(String[] args) {
        Parameters parameters = new Parameters();
        picocli.CommandLine commandLine = new picocli.CommandLine(parameters);
        commandLine.setUnmatchedArgumentsAllowed(false).parseArgs(args);

        if(!parameters.helpRequested) {
            String lJob = parameters.JobType.toLowerCase(Locale.ROOT);
            switch (lJob) {
                case "combine":
                    if(parameters.imageInputFile == null || parameters.archiveInputFile == null) {
                        System.out.println("Please input both image and archive file to combine by '-i/--image' and '-a/--archive' option!");
                        System.exit(-1);
                    } else {
                        try {
                            System.out.println("Start combine...");
                            File output;
                            if(parameters.taskOutputFile != null && parameters.taskOutputFile.exists()) output = parameters.taskOutputFile;
                            else output = parameters.archiveInputFile.getParentFile();

                            Combiner.setVerbose(parameters.isVerbose);
                            TaskResult result = Combiner.Combine(parameters.imageInputFile, parameters.archiveInputFile, output);
                            if(result.isTaskSuccess()) {
                                System.out.println("Combine task done! output: " + output.getAbsolutePath());
                            } else if(result.hasException()) {
                                throw result.getTaskException();
                            } else {
                                System.out.println("Error occurred while proceeding combine...\nError: " + result.getErrorCode());
                            }
                        } catch (Throwable e) {
                            System.out.println("Error occurred while proceeding combine... Exception: ");
                            e.printStackTrace();
                            System.exit(-1);
                        }
                    }
                    break;

                case "split":
                    if(parameters.splitInputFile == null) {
                        System.out.println("Please input file to split by '-f/--file' option!");
                        System.exit(-1);
                    } else {
                        try {
                            System.out.println("Start combine...");
                            File output;
                            if(parameters.taskOutputFile != null && parameters.taskOutputFile.exists()) output = parameters.taskOutputFile;
                            else output = parameters.splitInputFile.getParentFile();

                            Splitter.setVerbose(parameters.isVerbose);
                            TaskResult result = Splitter.Split(parameters.splitInputFile, output);
                            if(result.isTaskSuccess()) {
                                System.out.println("Split task done! output: " + output.getAbsolutePath());
                            } else if(result.hasException()) {
                                System.out.println("Error: " + result.getErrorCode());
                                throw result.getTaskException();
                            } else {
                                System.out.println("Error occurred while proceeding split...\nError: " + result.getErrorCode());
                            }
                        } catch (Throwable e) {
                            System.out.println("Error occurred while proceeding split... Exception: ");
                            e.printStackTrace();
                            System.exit(-1);
                        }
                    }
                    break;

                default:
                    System.out.println("Please input task type by '-t/--type=[\"combine\"/\"split\"]' option!");
                    System.exit(-1);
            }
        } else System.out.print(commandLine.getUsageMessage());
    }
}
