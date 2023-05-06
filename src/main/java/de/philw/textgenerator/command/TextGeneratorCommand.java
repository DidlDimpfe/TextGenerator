package de.philw.textgenerator.command;

import de.philw.textgenerator.letters.Font;
import de.philw.textgenerator.letters.Letters;
import de.philw.textgenerator.letters.LettersBuilder;
import de.philw.textgenerator.letters.Size;
import de.philw.textgenerator.utils.Direction;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TextGeneratorCommand extends Command {

    // /textgenerator test <input>
    // /textgenerator generate <input>
    // /textgenerator settings
    // /textgenerator setStart (<coordinates>)

    private Location start;
    private Direction direction;
    private final Font font;
    private final Size size;
    private final int horizontalSpace;
    private final int verticalSpace;
    private final ArrayList<HashMap<Location, BlockData>> lastChanges;


    public TextGeneratorCommand() {
        super("textgenerator", new String[]{"tg", "textgen", "tgen"}, "The command for the TextGenerator plugin",
                "textgenerator.use");
        lastChanges = new ArrayList<>();
        font = Font.QUARTZ;
        size = Size.THREEBYTHREE;
        horizontalSpace = 1;
        verticalSpace = 1;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            return;
        }
        Player player = (Player) sender;
        if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("help"))) {
            helpRequested(player);
        }
        if (args.length > 1 && args[0].equalsIgnoreCase("generate")) {
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i<args.length; i++) {
                builder.append(args[i]).append(" ");
            }
            generate(player, builder.substring(0, builder.toString().length()-1).toLowerCase());
        }
        if ((args.length == 1 || args.length == 4) && args[0].equalsIgnoreCase("setStart")) {
            if (args.length == 1) {
                setStart(player);
            } else {
                setStart(player, args[1], args[2], args[3]);
            }
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("undo")) {
            undo(player);
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("setDirection")) {
            setDirection(player, args[1]);
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }

    private void helpRequested(Player player) {
        player.sendMessage(ChatColor.GREEN + "The textgenerator command has the following options:");
        player.sendMessage(ChatColor.GREEN + "/textgenerator help");
        player.sendMessage(ChatColor.GREEN + "/textgenerator setStart (<coordinates>)");
        player.sendMessage(ChatColor.GREEN + "/textgenerator setDirection <input>)");
        player.sendMessage(ChatColor.GREEN + "/textgenerator generate <direction>");
        player.sendMessage(ChatColor.GREEN + "/textgenerator undo");
    }
    private void generate(Player player, String toGenerate) {
        if (start == null) {
            player.sendMessage(ChatColor.RED + "Before you do this you have to set the start location with /textgenerator setStart (<coordinates>)");
            return;
        }
        String sentencePattern = "[a-zA-Z_0-9 \\\\]+";
        if (!toGenerate.matches(sentencePattern)) {
            player.sendMessage(ChatColor.RED + "You only can use characters from a-z and numbers");
            return;
        }
        Direction nowDirection = direction;
        if (nowDirection == null) {
            nowDirection = Direction.valueOf(player.getFacing().toString()).getRightDirection();
        }
        HashMap<Location, BlockData> oldBlocks = new HashMap<>();
        String[] lines = toGenerate.split("\\\\n");
        int toBottom = 0;
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            int toRight = 0;
            int letterIndex = 0;
            if (lineIndex != 0) toBottom += size.getNeededVerticalBlocks() + verticalSpace;
            String line = lines[lineIndex];
            String[] wordsInLine = line.split(" ");
            for (int wordIndex = 0; wordIndex < wordsInLine.length; wordIndex++) {
                String word = wordsInLine[wordIndex];
                if (wordIndex != 0) toRight += horizontalSpace;
                if (word.isEmpty()) continue;
                for (Character letter: word.toCharArray()) {
                    String[][] letterData = getLetterData(letter, nowDirection);
                    if (letterIndex != 0) toRight += Objects.requireNonNull(letterData).length + horizontalSpace;
                    Location letterStartLocation = editStartLocation(toRight, toBottom, nowDirection);

                    LettersBuilder.build(Objects.requireNonNull(letterData), nowDirection, Objects.requireNonNull(letterStartLocation), oldBlocks);
                    letterIndex++;
                }
            }
        }
        lastChanges.add(oldBlocks);
    }

    private void setStart(Player player) {
        start = player.getLocation().getBlock().getLocation();
        player.sendMessage(ChatColor.GREEN + "StartLocation has been successfully set to " +
                start.getX() + ", " + start.getY() + ", " + start.getZ());
    }

    private void setStart(Player player, String x, String y, String z) {
        if (isNotNumeric(x) || isNotNumeric(y) || isNotNumeric(z)) {
            player.sendMessage(ChatColor.RED + "The coordinates have to be integers.");
            return;
        }
        start = new Location(player.getWorld(), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
        player.sendMessage(ChatColor.GREEN + "StartLocation has been successfully set to " +
                start.getX() + ", " + start.getY() + ", " + start.getZ());
    }

    private void setDirection(Player player, String direction) {
        try {
            Direction.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "That is not a valid direction. Try north, east, south or west!");
            return;
        }

        this.direction = Direction.valueOf(direction.toUpperCase());
        player.sendMessage(ChatColor.GREEN + "Successfully changed the direction to " + direction);
    }

    private void undo(Player player) {
        if (lastChanges.isEmpty()) {
            player.sendMessage(ChatColor.RED + "There's nothing to undo!");
            return;
        }
        HashMap<Location, BlockData> lastChange = lastChanges.get(lastChanges.size()-1);
        for (Location location: lastChange.keySet()) {
            Objects.requireNonNull(location.getWorld()).getBlockAt(location).setBlockData(lastChange.get(location));
        }
        player.sendMessage(ChatColor.GREEN + "Successfully undid the last change!");
        lastChanges.remove(lastChanges.size()-1);
    }

    // UTILITY METHODS

    private boolean isNotNumeric(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch(NumberFormatException e){
            return true;
        }
    }

    private Location editStartLocation(int toRight, int toBottom, Direction direction) {
        if (direction == Direction.NORTH) return start.clone().subtract(0, toBottom, toRight);
        if (direction == Direction.EAST) return start.clone().subtract(0, toBottom, 0).add(toRight, 0, 0);
        if (direction == Direction.SOUTH) return start.clone().subtract(0, toBottom, 0).add(0, 0, toRight);
        if (direction == Direction.WEST) return start.clone().subtract(toRight, toBottom, 0);
        return null;
    }

    private String[][] getLetterData(Character character, Direction direction) {
        Letters letters = size.getLetters();
        switch (character) {
            case 'a':
                return letters.getA(font, direction);
            case 'b':
                return letters.getB(font, direction);
            case 'c':
                return letters.getC(font, direction);
            case 'd':
                return letters.getD(font, direction);
            case 'e':
                return letters.getE(font, direction);
            case 'f':
                return letters.getF(font, direction);
            case 'g':
                return letters.getG(font, direction);
            case 'h':
                return letters.getH(font, direction);
            case 'i':
                return letters.getI(font, direction);
            case 'j':
                return letters.getJ(font, direction);
            case 'k':
                return letters.getK(font, direction);
            case 'l':
                return letters.getL(font, direction);
            case 'm':
                return letters.getM(font, direction);
            case 'n':
                return letters.getN(font, direction);
            case 'o':
                return letters.getO(font, direction);
            case 'p':
                return letters.getP(font, direction);
            case 'q':
                return letters.getQ(font, direction);
            case 'r':
                return letters.getR(font, direction);
            case 's':
                return letters.getS(font, direction);
            case 't':
                return letters.getT(font, direction);
            case 'u':
                return letters.getU(font, direction);
            case 'v':
                return letters.getV(font, direction);
            case 'w':
                return letters.getW(font, direction);
            case 'x':
                return letters.getX(font, direction);
            case 'y':
                return letters.getY(font, direction);
            case 'z':
                return letters.getZ(font, direction);
            case '1':
                return letters.get1(font, direction);
            case '2':
                return letters.get2(font, direction);
            case '3':
                return letters.get3(font, direction);
            case '4':
                return letters.get4(font, direction);
            case '5':
                return letters.get5(font, direction);
            case '6':
                return letters.get6(font, direction);
            case '7':
                return letters.get7(font, direction);
            case '8':
                return letters.get8(font, direction);
            case '9':
                return letters.get9(font, direction);
        }
        return null;
    }

}
