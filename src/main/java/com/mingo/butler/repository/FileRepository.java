package com.mingo.butler.repository;

import com.mingo.butler.domain.FileDetails;
import com.mingo.butler.domain.FileDetailsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Repository
public class FileRepository {

    private final static Logger log = LoggerFactory.getLogger(FileRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FileRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<FileDetails> getRowMapper() {
        return (resultSet, rowNum) -> new FileDetailsBuilder()
                .messageId(resultSet.getString("message_id"))
                .ticketId(resultSet.getString("ticket_id"))
                .createdDate(resultSet.getDate("created_date").toLocalDate())
                .url(resultSet.getString("url"))
                .build();
    }

    private Timestamp convertLocalDateToTimestamp(LocalDate date) {
        return Timestamp.from(date.atStartOfDay().toInstant(ZoneOffset.UTC));
    }

    private Map<String, Object> getFileDetailsParams(FileDetails fileDetails) {
        Map<String, Object> params = new HashMap<>();
        params.put("messageId", fileDetails.messageId());
        params.put("ticketId", fileDetails.ticketId());
        params.put("createdDate", fileDetails.createdDate());
        params.put("url", fileDetails.url());

        return params;
    }
}
