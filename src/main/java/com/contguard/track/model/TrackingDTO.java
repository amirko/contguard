package com.contguard.track.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrackingDTO extends TelemtryDTO {

    private Integer mmsi;




}
