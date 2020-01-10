package cn.jianchen.com.trade.api.admin.repository;


import cn.jianchen.com.trade.api.admin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IRoleRepository extends JpaRepository<Role, Integer> {

}