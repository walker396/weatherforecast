package com.complexica.itinerary.modules.system.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Itinerary Bean
 * @author Li He
 * @date 2021-02-10
 */
@Entity
@Setter
@Getter
@Table(name="itinerary")
public class Itinerary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "status")
    private String status;

    @Column(name = "create_time")
    @CreationTimestamp
    private Date createTime;

    @OneToMany(mappedBy = "itinerary",cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<PlanDetail> planDetails;


    /*public Itinerary() {
    }

    public Itinerary(String name, Long userId, String status, Date createTime) {
        this.name = name;
        this.userId = userId;
        this.status = status;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }*/


    public void copy(Itinerary source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
