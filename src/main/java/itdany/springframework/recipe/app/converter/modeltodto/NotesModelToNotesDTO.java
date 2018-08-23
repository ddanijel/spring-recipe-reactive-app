package itdany.springframework.recipe.app.converter.modeltodto;

import itdany.springframework.recipe.app.dto.NotesDTO;
import itdany.springframework.recipe.app.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesModelToNotesDTO implements Converter<Notes, NotesDTO> {

    @Synchronized
    @Nullable
    @Override
    public NotesDTO convert(Notes source) {
        if (source == null)
            return null;

        final NotesDTO notesDTO = new NotesDTO();
        notesDTO.setId(source.getId());
        notesDTO.setRecipeNotes(source.getRecipeNotes());
        return notesDTO;
    }
}
