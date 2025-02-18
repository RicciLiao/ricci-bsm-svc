package ricciliao.bsm.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import ricciliao.bsm.pojo.bo.BsmCodeDetailBo;
import ricciliao.bsm.pojo.dto.BsmCodeDetailDto;
import ricciliao.bsm.pojo.po.BsmCodeDetailPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BsmCodeDetailRepository extends JpaRepository<BsmCodeDetailPo, Long> {

    @Override
    void flush();

    @Override
    <S extends BsmCodeDetailPo> S saveAndFlush(S entity);

    @Override
    <S extends BsmCodeDetailPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<BsmCodeDetailPo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    BsmCodeDetailPo getReferenceById(Long aLong);

    @Override
    <S extends BsmCodeDetailPo> List<S> findAll(Example<S> example);

    @Override
    <S extends BsmCodeDetailPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends BsmCodeDetailPo> List<S> saveAll(Iterable<S> entities);

    @Override
    List<BsmCodeDetailPo> findAll();

    @Override
    List<BsmCodeDetailPo> findAllById(Iterable<Long> longs);

    @Override
    <S extends BsmCodeDetailPo> S save(S entity);

    @Override
    Optional<BsmCodeDetailPo> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(BsmCodeDetailPo entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends BsmCodeDetailPo> entities);

    @Override
    void deleteAll();

    @Override
    List<BsmCodeDetailPo> findAll(Sort sort);

    @Override
    Page<BsmCodeDetailPo> findAll(Pageable pageable);

    @Override
    <S extends BsmCodeDetailPo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends BsmCodeDetailPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends BsmCodeDetailPo> long count(Example<S> example);

    @Override
    <S extends BsmCodeDetailPo> boolean exists(Example<S> example);

    @Override
    <S extends BsmCodeDetailPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Query("select new ricciliao.bsm.pojo.dto.BsmCodeDetailDto(cd.id, cd.bsmCodeId, cd.code) from BsmCodeDetailPo cd " +
            "inner join BsmCodePo c on c.id = cd.bsmCodeId " +
            "where c.code = :bsmCode and cd.code = :bsmDetailCode")
    Optional<BsmCodeDetailDto> getByUniqueKey(@Param("bsmCode") String bsmCode,
                                              @Param("bsmDetailCode") String bsmDetailCode);

    @Query("select new ricciliao.bsm.pojo.bo.BsmCodeDetailBo(cp.code, cdp.code, cdp.description) " +
            "from BsmCodeDetailPo cdp " +
            "inner join BsmCodePo cp on cdp.bsmCodeId = cp.id " +
            "where cp.isActive = 1 " +
            "and cdp.isActive = 1")
    List<BsmCodeDetailBo> findAllForCache();
}
