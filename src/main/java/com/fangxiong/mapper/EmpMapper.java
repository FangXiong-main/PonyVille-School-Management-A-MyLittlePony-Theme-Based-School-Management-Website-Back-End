package com.fangxiong.mapper;

import com.fangxiong.pojo.Emp;
import com.fangxiong.pojo.EmpQueryParam;
import com.fangxiong.pojo.LoginResult;
import com.fangxiong.pojo.SexOption;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

//    //查询总记录数(使用pageHelper之后无需使用)
//    @Select("select count(*) from emp left join dept on emp.dept_id=dept.id")
//    long count();

    //查询当前页面的数据(带条件查询复杂的sql推荐在xml文件中配置)
    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc ")
    List<Emp> page(EmpQueryParam empQueryParam);

    //添加员工基本信息
    @Options(useGeneratedKeys = true ,keyProperty = "id") //使用MyBatis中的主键返回，并设置到Emp对象中
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) " +
            "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void save(Emp emp);

    void deleteById(List<Integer> ids);

    Emp getById(Integer id);

    void update(Emp emp);

    @MapKey("pos") //MyBatis误报告，忽略
    List<Map<String, Object>> countByJob();
    //下面是对List<Map<String,Object>>深度理解的步骤
    //Map<String,Object>是一个键值对集合，其中键为String类型，值为Object类型
    //其中记录了两条数据：（例子）1.pos为键，其值为教研主管 2.value为键，其值为1 （这两个数据是一个Map集合中的两个元素）
    //而这个Map又是List集合中的元素，List集合中的元素是Map集合，故每一个List元素都是一个Map集合
    //一个Map集合就是查询出来的一行的数据

    //这里可以直接使用Map<String,Object>来接收，但是使用SexOption来接收，可以更清晰
    //详细内容详见上方解析
    //List<SexOption> countBySex();

    //以下使用Map来进行封装
    @MapKey("name")
    List<Map<String,Object>> countByGender();

    @Select("select id,name from emp")
    List<Emp> getMasterlist();

    @Select("select * from emp where username=#{username} and password = #{password}")
    LoginResult selectByUsernameAndPassword(Emp emp);
}
