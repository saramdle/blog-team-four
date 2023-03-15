import PostPreviewCard from "./PostPrevieCard";

async function getPosts() {
  const res = await fetch("http://localhost:4000/posts", {
    next: { revalidate: 60 * 60 },
  });
  if (!res.ok) {
    throw new Error("Failed to fetch data");
  }
  return res.json();
}

export default async function PostPreview() {
  const posts: Post[] = await getPosts();
  return (
    <div className='grid grid-cols-1 gap-2 sm:grid-cols-2'>
      {posts.map((post: Post) => (
        <PostPreviewCard
          createdAt={post.createdAt}
          updatedAt={post.updatedAt}
          author={post.author}
          id={post.id}
          key={post.id}
          imgUrl={post.imgUrl}
          title={post.title}
          contents={post.contents}
        />
      ))}
    </div>
  );
}
