package cat.touffu.management.javafx.card;

import cat.touffu.management.components.cards.domain.CardStatus;

import java.util.Objects;

public record StatusChoice(
            CardStatus status,
            String text
    ) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StatusChoice that = (StatusChoice) o;
            return status == that.status;
        }

        @Override
        public int hashCode() {
            return Objects.hash(status);
        }
    }