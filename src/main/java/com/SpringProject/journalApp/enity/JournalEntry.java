package com.SpringProject.journalApp.enity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    @Nonnull
    private String title;
    private String content;
    private LocalDateTime date;

}
