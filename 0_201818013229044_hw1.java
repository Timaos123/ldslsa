public class HBaseTest {
    public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
        // create table descriptor
        String tableName = "mytable";
        HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(tableName));
        // create column descriptor
        HColumnDescriptor cf = new HColumnDescriptor("mycf");
        htd.addFamily(cf);
        // configure HBase
        Configuration configuration = HBaseConfiguration.create();
        HBaseAdmin hAdmin = new HBaseAdmin(configuration);
        hAdmin.createTable(htd);
        hAdmin.close();
        // put "mytable","abc","mycf:a","789"
        HTable table = new HTable(configuration, tableName);
        Put put = new Put("abc".getBytes());
        put.add("mycf".getBytes(), "a".getBytes(), "789".getBytes());
        table.put(put);
        table.close();
        System.out.println("put successfully");
    }
}

public class HDFSTest {
    public static void main(String[] args) throws IOException, URISyntaxException{
    String file= "hdfs://localhost:9000/文件路径";
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(URI.create(file), conf);
    FSDataInputStream in_stream = fs.open(new Path(file));
    BufferedReader in = new BufferedReader(new InputStreamReader(in_stream));
    String s;
    while ((s=in.readLine())!=null) {
    System.out.println(s);
    }
    in.close();
    fs.close();
    }
}