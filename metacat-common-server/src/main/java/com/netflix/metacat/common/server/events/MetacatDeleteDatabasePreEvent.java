package com.netflix.metacat.common.server.events;

import com.netflix.metacat.common.MetacatContext;
import com.netflix.metacat.common.dto.DatabaseDto;

import java.util.Objects;

public class MetacatDeleteDatabasePreEvent extends MetacatEvent {
    private final DatabaseDto dto;

    public MetacatDeleteDatabasePreEvent(DatabaseDto dto, MetacatContext metacatContext) {
        super( dto!=null?dto.getName():null, metacatContext);
        this.dto = dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetacatDeleteDatabasePreEvent)) return false;
        if (!super.equals(o)) return false;
        MetacatDeleteDatabasePreEvent that = (MetacatDeleteDatabasePreEvent) o;
        return Objects.equals(dto, that.dto);
    }

    public DatabaseDto getDto() {

        return dto;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(dto);
    }

    @Override
    public String toString() {
        return "MetacatDeleteDatabasePreEvent{dto=" + dto + '}';
    }
}
