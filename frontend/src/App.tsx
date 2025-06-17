import { useEffect, useState } from 'react'
import { createStory, getStories, type Story } from './services/api'
import StoryList from "./components/StoryList.tsx";

function App() {
    const [stories, setStories] = useState<Story[]>([])
    const [newTitle, setNewTitle] = useState('')
    const [newContent, setNewContent] = useState('')
    const [error, setError] = useState<string | null>(null)

    const refreshStories = async () => {
        try {
            const data = await getStories()
            setStories(data)
        } catch {
            setError('Kunne ikke hente stories')
        }
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        try {
            await createStory(newTitle, newContent)
            setNewTitle('')
            setNewContent('')
            refreshStories()
        } catch {
            setError('Fejl ved oprettelse')
        }
    }

    useEffect(() => {
        refreshStories()
    }, [])

    return (
        <div className="container py-5">
            <h1 className="text-center mb-4">Stories</h1>

            <form onSubmit={handleSubmit} className="card p-4 mb-4 shadow-sm">
                <div className="mb-3">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Titel"
                        value={newTitle}
                        onChange={(e) => setNewTitle(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <textarea
                        className="form-control"
                        placeholder="Indhold"
                        rows={3}
                        value={newContent}
                        onChange={(e) => setNewContent(e.target.value)}
                        required
                    ></textarea>
                </div>
                <button type="submit" className="btn btn-primary w-100">
                    Tilføj
                </button>
                {error && <p className="text-danger mt-2">{error}</p>}
            </form>

            <StoryList stories={stories} />
        </div>
    )
}

export default App
