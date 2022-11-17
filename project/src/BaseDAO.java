import org.postgresql.ds.PGSimpleDataSource;

public class BaseDAO {
    public void test() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setPortNumber(26257);
        ds.setDatabaseName("db-grpeinteilung-3751.Gruppeneinteilung");
        ds.setUrl("jdbc:postgresql://free-tier13.aws-eu-central-1.cockroachlabs.cloud:26257/Gruppeneinteilung?options=--cluster%3Ddb-grpeinteilung-3751&sslmode=verify-full");
        ds.setUser("testuser");
        ds.setPassword("_jnQ_7PPPz7j_LJ5PSCbCg");
        ds.setSslMode("require");
        try{
        System.out.println(ds.getConnection("testuser", "_jnQ_7PPPz7j_LJ5PSCbCg\""));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
