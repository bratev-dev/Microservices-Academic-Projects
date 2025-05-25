package com.unicauca.CoordinatorService.domain.model;

import com.unicauca.CoordinatorService.domain.state.*;
import com.unicauca.CoordinatorService.domain.state.ProjectState;
/**
 *
 * @author jpala
 */
public enum ProjectStatus {
    APPROVED {
        @Override
        public ProjectState getState() {
            return new ApprovedState();
        }
    },
    ASSIGNED {
        @Override
        public ProjectState getState() {
            return new AssignedState();
        }
    },
    COMPLETED {
        @Override
        public ProjectState getState() {
            return new CompletedState();
        }
    },
    PENDING {
        @Override
        public ProjectState getState() {
            return new PendingState();
        }
    },
    REJECTED {
        @Override
        public ProjectState getState() {
            return new RejectState();
        }
    };

    public abstract ProjectState getState();
}
