package db.migration;

import com.github.javafaker.Faker;
import de.biobank.dataservice.entity.Profession;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.util.Locale;

public class V2__InsertRandomDoctors extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(
                new SingleConnectionDataSource(context.getConnection(), true));
        final Faker faker = new Faker(new Locale("de"));

        for (int i = 1000; i < 2000; i++) {
            String sql = String.format("insert into doctor"
                            + " (id, first_name, last_name, zip, city, profession) values"
                            + " ('%d', '%s', '%s', '%s', '%s', '%s')",
                    i, faker.name().firstName(), faker.name().lastName(), faker.address().city(), faker.address().zipCode(), Profession.randomProfession());
            jdbcTemplate.execute(sql);
        }

    }
}
