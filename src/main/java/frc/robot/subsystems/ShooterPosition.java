package frc.robot.subsystems;

public enum ShooterPosition {
    FLAT("FLAT", 19.5 - 1),
    AMP("AMP", 103 - 1), // prev 108.7 prev prev 90 now 95
    SPEAKER("SPEAKER", 62.9 - 1), // 62.9 -> 64 -> 66
    SOURCE("SOURCE", 49 - 1);
    
    public final double position;
    public final String name;
    
    private ShooterPosition(String name, double position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public String toString() {
        return name;
    }
}