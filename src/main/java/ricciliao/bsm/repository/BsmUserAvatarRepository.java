package ricciliao.bsm.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.bsm.pojo.po.BsmUserAvatarPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BsmUserAvatarRepository extends JpaRepository<BsmUserAvatarPo, Long> {

    @Override
    void flush();

    @Override
    <S extends BsmUserAvatarPo> S saveAndFlush(S entity);

    @Override
    <S extends BsmUserAvatarPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<BsmUserAvatarPo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    BsmUserAvatarPo getReferenceById(Long aLong);

    @Override
    <S extends BsmUserAvatarPo> List<S> findAll(Example<S> example);

    @Override
    <S extends BsmUserAvatarPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends BsmUserAvatarPo> List<S> saveAll(Iterable<S> entities);

    @Override
    List<BsmUserAvatarPo> findAll();

    @Override
    List<BsmUserAvatarPo> findAllById(Iterable<Long> longs);

    @Override
    <S extends BsmUserAvatarPo> S save(S entity);

    @Override
    Optional<BsmUserAvatarPo> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(BsmUserAvatarPo entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends BsmUserAvatarPo> entities);

    @Override
    void deleteAll();

    @Override
    List<BsmUserAvatarPo> findAll(Sort sort);

    @Override
    Page<BsmUserAvatarPo> findAll(Pageable pageable);

    @Override
    <S extends BsmUserAvatarPo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends BsmUserAvatarPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends BsmUserAvatarPo> long count(Example<S> example);

    @Override
    <S extends BsmUserAvatarPo> boolean exists(Example<S> example);

    @Override
    <S extends BsmUserAvatarPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
