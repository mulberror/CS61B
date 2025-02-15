package capers;

import java.io.File;
import java.io.IOException;

/** A repository for Capers 
 * @author mulberror
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Utils.join(".capers"); // TODO Hint: look at the `join`
    static final File STORY_FILE = Utils.join(".capers", "story");
    static final File DOGS_FOLDER = Utils.join(".capers", "dogs");

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        if (!CAPERS_FOLDER.exists()) {
            try {
                if (!CAPERS_FOLDER.mkdir()) {
                    System.err.println("Failed to create folder " + CAPERS_FOLDER.getAbsolutePath());
                }
            } catch (SecurityException ignored) {
            }
        }
        if (!STORY_FILE.exists()) {
            try {
                if (!STORY_FILE.createNewFile()) {
                    System.err.println("Failed to create file " + STORY_FILE.getAbsolutePath());
                }
            } catch (SecurityException | IOException ignored) {
            }
        }
        if (!DOGS_FOLDER.exists()) {
            try {
                if (!DOGS_FOLDER.mkdir()) {
                    System.err.println("Failed to create folder " + DOGS_FOLDER.getAbsolutePath());
                }
            } catch (SecurityException ignored) {
            }
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        String storyContent = Utils.readContentsAsString(STORY_FILE);
        storyContent += text + "\n";
        Utils.writeContents(STORY_FILE, storyContent);
        System.out.println(storyContent);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog newDog = new Dog(name, breed, age);
        newDog.saveDog();
        System.out.println(newDog.toString());
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog dog = Dog.fromFile(name);
        dog.haveBirthday();
        dog.saveDog();
    }
}
