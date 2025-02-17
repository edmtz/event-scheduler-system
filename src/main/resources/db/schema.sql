-- Modify event_participants to remove participations when a user is deleted
ALTER TABLE event_participants
DROP CONSTRAINT IF EXISTS event_participants_user_id_fkey;

ALTER TABLE event_participants
ADD CONSTRAINT event_participants_user_id_fkey
FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

-- Modify event_participants to remove participations when an event is deleted
ALTER TABLE event_participants
DROP CONSTRAINT IF EXISTS event_participants_event_id_fkey;

ALTER TABLE event_participants
ADD CONSTRAINT event_participants_event_id_fkey
FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE;

-- Allow events to remain in the database without a creator
ALTER TABLE events
ALTER COLUMN created_by DROP NOT NULL;

ALTER TABLE events
DROP CONSTRAINT IF EXISTS events_created_by_fkey;

ALTER TABLE events
ADD CONSTRAINT events_created_by_fkey
FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL;