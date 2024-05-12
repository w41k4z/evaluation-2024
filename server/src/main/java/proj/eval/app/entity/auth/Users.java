package proj.eval.app.entity.auth;

import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.annotation.relationship.OneToOne;
import proj.w41k4z.orm.database.Repository;
import proj.w41k4z.orm.database.connectivity.DatabaseConnection;
import proj.w41k4z.orm.database.request.Condition;
import proj.w41k4z.orm.database.request.Operator;

@Getter
@Setter
@Entity(table = "users")
public class Users extends Repository<Users, Long> {

  @Id
  @Column
  private Long id;

  @Column
  private String username;

  @Column
  private String password;

  @OneToOne
  @Column(name = "group_id")
  private Groups group;

  public Users findByUsername(DatabaseConnection connection, String username)
    throws Exception {
    return new Users()
      .findOne(connection, Condition.WHERE("_username_", Operator.E, username));
  }
}
