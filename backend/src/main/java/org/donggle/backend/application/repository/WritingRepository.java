package org.donggle.backend.application.repository;

import org.donggle.backend.domain.writing.Writing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WritingRepository extends JpaRepository<Writing, Long> {
    List<Writing> findAllByCategoryId(final Long categoryId);

    int countByCategoryId(Long id);

    int countByNextWritingId(Long nextWritingId);

    @Query("select w from Writing w " +
            "where w.category.id = :categoryId and " +
            "w.nextWriting is null")
    Optional<Writing> findLastWritingByCategoryId(@Param("categoryId") final Long categoryId);

    @Query("select w from Writing w " +
            "where w.nextWriting.id = :writingId")
    Optional<Writing> findPreWritingByWritingId(@Param("writingId") final Long writingId);
}
