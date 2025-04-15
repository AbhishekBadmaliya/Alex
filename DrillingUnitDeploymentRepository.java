package org.bisag.ocbis.repository;

import java.util.List;
import java.util.Map;

import org.bisag.ocbis.models.DrillingUnitDeployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DrillingUnitDeploymentRepository extends JpaRepository<DrillingUnitDeployment, Long> {

    @Query(nativeQuery = true, value = """
                                             SELECT * FROM public.drill_drilling_unit_deployment where id=?1
            ORDER BY id ASC

                                               """)
    List<Map<String, Object>> getAlreadyFilledDetailsFormWise(Long id);

    @Query(nativeQuery = true, value = """
            SELECT * FROM drill_drilling_unit_deployment WHERE id = ?1
            """)
    DrillingUnitDeployment findByFormId(long id);

    @Query(nativeQuery = true, value = """
            SELECT * FROM fsp_region_master
            WHERE region_name NOT IN ('REMOTE SENSING AND AERIAL SURVEYS', 'CENTRAL HEADQUARTERS' ,'DGCO' , 'PAO','MARINE AND COASTAL SURVEY');
            """)
    List<Map<String, Object>> getRegionName();
}
