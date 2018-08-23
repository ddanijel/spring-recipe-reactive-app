package itdany.springframework.recipe.app.converter.dtotomodel;

import itdany.springframework.recipe.app.dto.NotesDTO;
import itdany.springframework.recipe.app.model.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesDTOToNotesModel implements Converter<NotesDTO, Notes> {
    @Override
    public Notes convert(NotesDTO source) {
        if (source == null)
            return null;

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
