package daisy.teaming.mapper;

import daisy.teaming.bean.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupMapper {

    @Select("select * from group where groupId=#{groupId}")
    List<Group>getGroup(int groupId);
}
