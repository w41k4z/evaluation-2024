package proj.eval.app.entity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.annotation.relationship.OneToOne;
import proj.w41k4z.orm.database.Repository;
import proj.w41k4z.orm.database.connectivity.DatabaseConnection;

@Getter
@Setter
@Entity(table = "quotes")
public class Quote extends Repository<Quote, Long> {

  @Id
  @Column
  private Long id;

  @Column(name = "action_date")
  private Date date;

  @Column(name = "users_id")
  private Long userId;

  @Column(name = "house_types_id")
  private Long houseTypesId;

  @OneToOne
  @Column(name = "house_types_id")
  private HouseType houseType;

  @Column(name = "finition_types_id")
  private Long finitionTypesId;

  @OneToOne
  @Column(name = "finition_types_id")
  private FinitionType finitionType;

  @Column(name = "finition_type_majoration")
  private Double finitionTypeMajoration;

  @Column(name = "construction_start_date")
  private Date constructionStartDate;

  @Column(name = "construction_end_date")
  private Date constructionEndDate;

  @Column(name = "total_price")
  private Double totalPrice;

  public void create(
    DatabaseConnection connection,
    HouseType houseType,
    FinitionType finitionType,
    LocalDate constructionStartDate,
    Long userId
  )
    throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException, SecurityException, IOException, SQLException {
    this.setDate(Date.valueOf(LocalDate.now()));
    this.setConstructionStartDate(Date.valueOf(constructionStartDate));
    this.setConstructionEndDate(
        Date.valueOf(constructionStartDate.plusDays(houseType.getDuration()))
      );
    this.setUserId(userId);
    this.setHouseTypesId(houseType.getId());
    this.setFinitionTypesId(finitionType.getId());
    this.setFinitionTypeMajoration(finitionType.getMajoration());
    //TODO: check if the calcul is true
    this.setTotalPrice(
        houseType.getTotalPrice() +
        (houseType.getTotalPrice() * finitionType.getMajoration())
      );
    this.create(connection);
  }
}
