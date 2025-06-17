export interface Story {
    id: number
    title: string
    content: string
}

export async function getStories(): Promise<Story[]> {
    const response = await fetch(`/api/stories`)
    if (!response.ok) {
        throw new Error('Failed to fetch stories')
    }
    return await response.json()
}

export async function createStory(title: string, content: string): Promise<Story> {
    const response = await fetch(`/api/stories`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ title, content })
    })

    if (!response.ok) {
        throw new Error('Failed to create story')
    }
    return await response.json()
}

