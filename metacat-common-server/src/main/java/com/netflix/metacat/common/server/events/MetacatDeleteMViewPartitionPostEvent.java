package com.netflix.metacat.common.server.events;

import com.netflix.metacat.common.MetacatContext;
import com.netflix.metacat.common.QualifiedName;

import java.util.List;
import java.util.Objects;

public class MetacatDeleteMViewPartitionPostEvent extends MetacatEvent {
    private final List<String> partitionIds;

    public MetacatDeleteMViewPartitionPostEvent(QualifiedName name, List<String> partitionIds, MetacatContext metacatContext) {
        super(name, metacatContext);
        this.partitionIds = partitionIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetacatDeleteMViewPartitionPostEvent)) return false;
        if (!super.equals(o)) return false;
        MetacatDeleteMViewPartitionPostEvent that = (MetacatDeleteMViewPartitionPostEvent) o;
        return Objects.equals(partitionIds, that.partitionIds);
    }

    public List<String> getPartitionIds() {
        return partitionIds;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(partitionIds);
    }

    @Override
    public String toString() {
        return "MetacatDeleteMViewPartitionPostEvent{" +
                ", partitions=" + partitionIds +
                '}';
    }
}
