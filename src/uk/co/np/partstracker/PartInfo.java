package uk.co.np.partstracker;

public class PartInfo {
    public int count;
    public String partNumber;
    public String name;
    public PartInfo() {
    }
    public PartInfo(int count, String partNumber, String name) {
        this.count = count;
        this.partNumber = partNumber;
        this.name = name;
    }

    public static PartInfo DeserializeFromCSV(String info) throws RuntimeException {
        //Format: Name,PartNum,count
        String[] values = info.split(",");
        if (values.length < 3) {
            throw new RuntimeException("Incorrect Argument Length");
        }
        String name = values[0];
        String partNum = values[1];
        int count = Integer.parseInt(values[2]);
        PartInfo part = new PartInfo();
        part.count = count;
        part.partNumber = partNum;
        part.name = name;
        return part;
    }

    public static String SerializeToCSV(PartInfo info) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(info.name);
        buffer.append(',');
        buffer.append(info.partNumber);
        buffer.append(',');
        buffer.append(info.count);
        return buffer.toString();
    }

    @Override
    public String toString() {
        return SerializeToCSV(this);
    }
}
