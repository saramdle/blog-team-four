export const fetchCache = "force-no-store";

type Comment = {
  id: number;
  author: string;
  contents: string;
  post: number;
  createdAt: string;
  updatedAt: string;
};

async function getComments() {
  const res = await fetch(`http://localhost:4000/comments`);
  if (!res.ok) {
    throw new Error("Failed to fetch data");
  }
  return res.json();
}

export default async function Comments({ postId }: { postId: number }) {
  const comments = await getComments();
  const selectedComments = comments.filter(
    (comment: Comment) => comment.post == postId
  );

  return (
    <div>
      {selectedComments.map((comment: Comment) => (
        <div key={comment.id}>
          <div>{comment.contents}</div>
          <div>{comment.author}</div>
          <div>{comment.createdAt}</div>
        </div>
      ))}
    </div>
  );
}
