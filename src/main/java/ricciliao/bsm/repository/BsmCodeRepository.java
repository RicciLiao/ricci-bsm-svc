package ricciliao.bsm.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.bsm.pojo.po.BsmCodePo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BsmCodeRepository extends JpaRepository<BsmCodePo, Long> {
    @Override
    void flush();

    @Override
    <S extends BsmCodePo> S saveAndFlush(S entity);

    @Override
    <S extends BsmCodePo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<BsmCodePo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    BsmCodePo getReferenceById(Long aLong);

    @Override
    <S extends BsmCodePo> List<S> findAll(Example<S> example);

    @Override
    <S extends BsmCodePo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends BsmCodePo> List<S> saveAll(Iterable<S> entities);

    @Override
    List<BsmCodePo> findAll();

    @Override
    List<BsmCodePo> findAllById(Iterable<Long> longs);

    @Override
    <S extends BsmCodePo> S save(S entity);

    @Override
    Optional<BsmCodePo> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(BsmCodePo entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends BsmCodePo> entities);

    @Override
    void deleteAll();

    @Override
    List<BsmCodePo> findAll(Sort sort);

    @Override
    Page<BsmCodePo> findAll(Pageable pageable);

    @Override
    <S extends BsmCodePo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends BsmCodePo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends BsmCodePo> long count(Example<S> example);

    @Override
    <S extends BsmCodePo> boolean exists(Example<S> example);

    @Override
    <S extends BsmCodePo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
