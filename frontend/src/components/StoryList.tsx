import type {Story} from '../services/api'

interface Props {
    stories: Story[]
}

export default function StoryList({ stories }: Props) {
    if (stories.length === 0) {
        return <p className="text-center text-muted">Ingen stories endnu...</p>
    }

    return (
        <div className="row g-3">
            {stories.map((story) => (
                <div className="col-12 col-md-6" key={story.id}>
                    <div className="card h-100 shadow-sm">
                        <div className="card-body">
                            <h5 className="card-title">{story.title}</h5>
                            <p className="card-text">{story.content}</p>
                        </div>
                    </div>
                </div>
            ))}
        </div>
    )
}
