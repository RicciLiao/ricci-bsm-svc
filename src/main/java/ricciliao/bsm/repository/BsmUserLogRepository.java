package ricciliao.bsm.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.bsm.pojo.po.BsmUserLogId;
import ricciliao.bsm.pojo.po.BsmUserLogPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BsmUserLogRepository extends JpaRepository<BsmUserLogPo, BsmUserLogId> {

    @Override
    void flush();

    @Override
    <S extends BsmUserLogPo> S saveAndFlush(S entity);

    @Override
    <S extends BsmUserLogPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<BsmUserLogPo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<BsmUserLogId> bsmUserLogIds);

    @Override
    void deleteAllInBatch();

    @Override
    BsmUserLogPo getReferenceById(BsmUserLogId bsmUserLogId);

    @Override
    <S extends BsmUserLogPo> List<S> findAll(Example<S> example);

    @Override
    <S extends BsmUserLogPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends BsmUserLogPo> List<S> saveAll(Iterable<S> entities);

    @Override
    List<BsmUserLogPo> findAll();

    @Override
    List<BsmUserLogPo> findAllById(Iterable<BsmUserLogId> bsmUserLogIds);

    @Override
    <S extends BsmUserLogPo> S save(S entity);

    @Override
    Optional<BsmUserLogPo> findById(BsmUserLogId bsmUserLogId);

    @Override
    boolean existsById(BsmUserLogId bsmUserLogId);

    @Override
    long count();

    @Override
    void deleteById(BsmUserLogId bsmUserLogId);

    @Override
    void delete(BsmUserLogPo entity);

    @Override
    void deleteAllById(Iterable<? extends BsmUserLogId> bsmUserLogIds);

    @Override
    void deleteAll(Iterable<? extends BsmUserLogPo> entities);

    @Override
    void deleteAll();

    @Override
    List<BsmUserLogPo> findAll(Sort sort);

    @Override
    Page<BsmUserLogPo> findAll(Pageable pageable);

    @Override
    <S extends BsmUserLogPo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends BsmUserLogPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends BsmUserLogPo> long count(Example<S> example);

    @Override
    <S extends BsmUserLogPo> boolean exists(Example<S> example);

    @Override
    <S extends BsmUserLogPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
