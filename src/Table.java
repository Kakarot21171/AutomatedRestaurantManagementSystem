public class Table {
    private int tableNumber;
    private String status;
    private String assignedServer;

    public Table(int tableNumber, String assignedServer) {
        this.tableNumber = tableNumber;
        this.assignedServer = assignedServer;
        this.status = "open";
    }

    public int getTableNumber() {
        return this.tableNumber;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedServer() {
        return this.assignedServer;
    }

    public void setAssignedServer(String assignedServer) {
        this.assignedServer = assignedServer;
    }

    public String toString() {
        return "Table" + this.tableNumber + "[" + this.status + "]- Server: " + this.assignedServer;
    }
}