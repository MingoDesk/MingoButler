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
import java.util.List;
import java.util.Map;

@Repository
public class FileRepository {

    private final static Logger log = LoggerFactory.getLogger(FileRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT = "INSERT INTO file_data " +
            "(ticket_id, message_id, url, created_date) " +
            "VALUES (:ticketId, :messageId, :url, :createdDate)";

    private static final String FIND_BY_TICKET_ID_AND_MESSAGE_ID = "SELECT * FROM file_data " +
            "WHERE ticket_id = :ticketId " +
            "AND message_id = :messageId";

    public FileRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(FileDetails fileDetails) {
        jdbcTemplate.update(INSERT, getFileDetailsParams(fileDetails));
    }

    public List<FileDetails> findByTicketIdAndMessageId(String ticketId, String messageId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("ticketId", ticketId);
        queryParams.put("messageId", messageId);

        return jdbcTemplate.query(FIND_BY_TICKET_ID_AND_MESSAGE_ID, queryParams, getRowMapper());
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
