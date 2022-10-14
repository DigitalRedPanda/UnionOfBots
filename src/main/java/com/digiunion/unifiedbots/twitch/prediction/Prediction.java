/*
 * package com.digiunion.unifiedbots.twitch.prediction;
 * 
 * import javax.persistence.Column;
 * import javax.persistence.Entity;
 * import javax.persistence.GeneratedValue;
 * import javax.persistence.Id;
 * import javax.persistence.SequenceGenerator;
 * import javax.persistence.Table;
 * import javax.persistence.UniqueConstraint;
 * import static javax.persistence.GenerationType.SEQUENCE;
 * 
 * import lombok.Data;
 * import lombok.NonNull;
 * import lombok.RequiredArgsConstructor;
 * 
 * @Entity
 * 
 * @Table(name = "prediction", uniqueConstraints = {
 * 
 * @UniqueConstraint(name = "unique_prediction_id", columnNames =
 * "prediction_id") })
 * 
 * @Data
 * 
 * @RequiredArgsConstructor
 * public class Prediction {
 * 
 * @Id
 * 
 * @SequenceGenerator(name = "prediction_sequence", sequenceName =
 * "prediction_sequence", allocationSize = 1)
 * 
 * @GeneratedValue(strategy = SEQUENCE, generator = "prediction_sequence")
 * 
 * @Column(name = "prediction_id", nullable = false, columnDefinition = "LONG")
 * Long id;
 * 
 * @Column(name = "channel_name", nullable = false, columnDefinition =
 * "VARCHAR(30)")
 * 
 * @NonNull
 * String channelName;
 * 
 * @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(45)")
 * 
 * @NonNull
 * String title;
 * 
 * @Column(name = "duration", nullable = false, columnDefinition = "INTEGER")
 * 
 * @NonNull
 * Integer duration;
 * 
 * @Column(name = "outcomes", nullable = false, columnDefinition =
 * "ARRAY[VARCHAR(25)]")
 * 
 * @NonNull
 * String[] outcomes;
 * 
 * }
 */