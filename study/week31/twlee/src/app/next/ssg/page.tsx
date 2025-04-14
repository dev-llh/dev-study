type Data = {
  title?: string
  content?: string
}

export default async function Page() {
  let data: Data = {}
  try {
    const res = await fetch('http://localhost:3000/data.txt')
    data = await res.json()
  } catch (error) {
    console.error('Failed to fetch data for SSG:', error);
    // 선택적으로 fallback data 반환 가능
    data = {
      title: 'fallback data',
      content: 'fallback'
    } satisfies Data
  }

  return (
    <div>
      hello
      <p>title: {data.title}</p>
      <p>content: {data.content}</p>
    </div>
  )
}