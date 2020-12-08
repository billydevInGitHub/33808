package billydev.mapper;

import billydev.entity.ClazzExtend;
import org.apache.ibatis.annotations.Param;

/**
 * @author Billy
 */
public interface ClazzMapper {
    /**
     * select class with students by class id.
     * @param  id class id;
     * @return ClazzExtend entity with students.
     */
    ClazzExtend selectWithStudentsById(@Param("id") int id);
}
