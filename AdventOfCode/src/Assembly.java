import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Assembly {

    private final List<Instruction> instructions;
    private static final Register registerA = new Register();
    private static final Register registerB = new Register();
    private static final Register registerC = new Register();
    private static final Register registerD = new Register();


    private Assembly(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public static void main(String[] args) {
        Assembly assembly = new Assembly(new ArrayList<>());
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            assembly.addInstruction(Instruction.create(in.nextLine()));
        }
        assembly.executeAllInstructions();
        System.out.println(getNamedRegister("a").value);
    }

    private void executeAllInstructions() {
        int instructionLocation = 0;
        while (instructionLocation < instructions.size()) {
            Instruction instructionToExecute = instructions.get(instructionLocation);
            instructionToExecute.execute();
            Optional<Integer> newInstruction = instructionToExecute.nextInstructionNumber();
            instructionLocation += newInstruction.orElse(1);
        }
    }

    private void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

    public static Register getNamedRegister(String s) {
        switch (s) {
            case "a":
                return registerA;
            case "b":
                return registerB;
            case "c":
                return registerC;
            case "d":
                return registerD;
            default:
                throw new IllegalArgumentException(s);
        }
    }
}

abstract class Instruction {

    protected Instruction() { }

    abstract void execute();

    abstract Optional<Integer> nextInstructionNumber();

    public static Instruction create(String s) {
        String[] split = s.split(" ");
        Register registerInSlot1 = null;
        Integer integerInSlot1 = null;
        try {
            registerInSlot1 = Assembly.getNamedRegister(split[1]);
        } catch (IllegalArgumentException expected) {
            integerInSlot1 = Integer.valueOf(split[1]);
        }
        switch (split[0]) {
            case "cpy":
                Register registerInSlot2 = Assembly.getNamedRegister(split[2]);
                if (integerInSlot1 != null) {
                    return new SimpleCopyInstruction(integerInSlot1, registerInSlot2);
                } else {
                    return new RegisterCopyInstruction(registerInSlot1, registerInSlot2);
                }
            case "inc":
                return new IncrementInstruction(registerInSlot1);
            case "dec":
                return new DecrementInstruction(registerInSlot1);
            case "jnz":
                if (integerInSlot1 != null) {
                    return new JumpNonZeroInstructionDirect(integerInSlot1, Integer.valueOf(split[2]));
                } else {
                    return new JumpNonZeroInstruction(registerInSlot1, Integer.valueOf(split[2]));
                }
            default:
                throw new IllegalStateException();
        }
    }
}

class JumpNonZeroInstructionDirect extends Instruction {
    private final Integer valToTest;
    private final Integer integer;

    public JumpNonZeroInstructionDirect(Integer valToTest, Integer integer) {
        this.valToTest = valToTest;
        this.integer = integer;
    }

    @Override
    void execute() {

    }

    @Override
    Optional<Integer> nextInstructionNumber() {
        return (valToTest == 0) ? Optional.empty() : Optional.of(integer);
    }
}

class JumpNonZeroInstruction extends Instruction {

    private final Register namedRegister;
    private final Integer integer;

    public JumpNonZeroInstruction(Register namedRegister, Integer integer) {

        this.namedRegister = namedRegister;
        this.integer = integer;
    }

    @Override
    void execute() {

    }

    @Override
    Optional<Integer> nextInstructionNumber() {
        return (namedRegister.value == 0) ? Optional.empty() : Optional.of(integer);
    }
}

class DecrementInstruction extends Instruction {
    private final Register src;

    public DecrementInstruction(Register src) {
        this.src = src;
    }

    @Override
    void execute() {
        src.decrement();
    }

    @Override
    Optional<Integer> nextInstructionNumber() {
        return Optional.empty();
    }
}

class IncrementInstruction extends Instruction {

    private final Register src;

    public IncrementInstruction(Register src) {
        this.src = src;
    }

    @Override
    void execute() {
        src.increment();
    }

    @Override
    Optional<Integer> nextInstructionNumber() {
        return Optional.empty();
    }
}

class RegisterCopyInstruction extends Instruction {

    private final Register src;
    private final Register dst;

    public RegisterCopyInstruction(Register src, Register dst) {
        this.src = src;
        this.dst = dst;
    }

    @Override
    void execute() {
        dst.copyValue(src.value);
    }

    @Override
    Optional<Integer> nextInstructionNumber() {
        return Optional.empty();
    }
}

class SimpleCopyInstruction extends Instruction {

    private final Register target;
    private final int newVal;

    public SimpleCopyInstruction(int newVal, Register target) {
        this.newVal = newVal;
        this.target = target;
    }

    @Override
    void execute() {
        target.copyValue(newVal);
    }

    @Override
    Optional<Integer> nextInstructionNumber() {
        return Optional.empty();
    }
}

class Register {

    private static final int START_VALUE = 0;

    int value;

    public Register() {
        this.value = START_VALUE;
    }

    public void decrement() {
        this.value--;
    }

    public void increment() {
        this.value++;
    }

    public void copyValue(int value) {
        this.value = value;
    }

}

